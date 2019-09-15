import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Item } from './item';
import { ItemService } from './item.service';
import { Observable } from 'rxjs';

@Injectable({
	providedIn: 'root'
})
export class CartService {
	items: Item[] = [];
	apiUrl = 'http://localhost:8080';

	constructor(private http: HttpClient) {}

	getItemInCart(): Item[] {
		return this.items;
	}
	deleteByIndex(index: number) {
		this.items.splice(index, 1);
	}
	emptyCart() {
		this.items = [];
	}

	addToCart(item: Item, qty: number) {
		let itemAlreadyInCart = false;
		this.items = this.items.map((i) => {
			if (i.id == item.id) {
				i.quantity += qty;
				itemAlreadyInCart = true;
			}
			return i;
		});
		if (!itemAlreadyInCart) {
			const newItem = new Item(item.name, item.price, item.quantity, item.category, item.imgUrl);
			newItem.id = item.id;
			this.items.push(newItem);
		}
	}

	// purchase(shoppingItems: Item[]): Observable<null> {
	// 	const url = `${this.apiUrl}/purchase`;
	// 	return this.http.post<null>(url, shoppingItems);
	// }
}
