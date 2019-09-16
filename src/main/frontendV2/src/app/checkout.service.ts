import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Item } from './item';
import { Observable } from 'rxjs';

@Injectable({
	providedIn: 'root'
})
export class CheckoutService {
	itemCart: Item[] = [];
	apiUrl = 'http://localhost:8080';

	constructor(private http: HttpClient) {}

	addCart(item: Item, qty: number) {
		this.itemCart.push(item);
	}

	// addCart(item: Item, qty: number) {
	// 	let itemInCart = false;
	// 	this.itemCart = this.itemCart.map((i) => {
	// 		if (i.id == item.id) {
	// 			i.quantity += qty;
	// 			itemInCart = true;
	// 		}
	// 		return i;
	// 	});
	// 	if (!itemInCart) {
	// 		const newItem = new Item(item.name, item.price, item.quantity, item.category, item.imgUrl);
	// 		newItem.id = item.id;
	// 		this.itemCart.push(newItem);
	// 	}
	// }
	getItemCart(): Item[] {
		return this.itemCart;
	}
	removeItemCart(index: number) {
		this.itemCart.splice(index, 1);
	}
	emptyCart() {
		this.itemCart = [];
	}

	purchaseItems(shopItems: Item[]): Observable<null> {
		const url = `${this.apiUrl}/purchase`;
		return this.http.post<null>(url, shopItems);
	}
}
