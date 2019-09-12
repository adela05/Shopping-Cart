import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Item } from './item';
import { Observable } from 'rxjs';

@Injectable({
	providedIn: 'root'
})
export class CartService {
	itemsInCart: Item[] = [];
	apiUrl = 'http://localhost:8080';

	constructor(private http: HttpClient) {}

	addToCart(item: Item, qty: number) {
		let itemAlreadyInCart = false;
		this.itemsInCart = this.itemsInCart.map((i) => {
			if (i.id == item.id) {
				i.quantity += qty;
				itemAlreadyInCart = true;
			}
			return i;
		});
		if (!itemAlreadyInCart) {
			const newItem = new Item(item.name, item.price, item.quantity, item.category, item.imgUrl);
			newItem.id = item.id;
			this.itemsInCart.push(newItem);
		}
	}
	getItemsInCart(): Item[] {
		return this.itemsInCart;
	}
	removeItemFromCart(index: number) {
		this.itemsInCart.splice(index, 1);
	}
	emptyCart() {
		this.itemsInCart = [];
	}
	purchase(shoppingItems: Item[]): Observable<null> {
		const url = `${this.apiUrl}/purchase`;
		return this.http.post<null>(url, shoppingItems);
	}
}
