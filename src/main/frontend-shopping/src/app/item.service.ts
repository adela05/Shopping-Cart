import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Item } from './item';

@Injectable({
	providedIn: 'root'
})
export class ItemService {
	items: Item[] = [];
	apiUrl = 'http://localhost:8080';

	constructor(private http: HttpClient) {}
	// Getting all items from backend
	getItem(): Observable<Item[]> {
		const url = `${this.apiUrl}/shopping`;
		return this.http.get<Item[]>(url);
	}
	// deletes the item by Id
	deleteItem(id: number): Observable<Item> {
		const url = `${this.apiUrl}/shopping/${id}`;
		return this.http.delete<Item>(url);
	}
	// This will be for updating the qty for each item
	updateItem(
		id: number,
		name: string,
		price: number,
		quantity: number,
		category: string,
		imgUrl: string
	): Observable<Item> {
		const newItem = new Item(name, price, quantity, category, imgUrl);
		const url = `${this.apiUrl}/shopping/${id}`;
		return this.http.put<Item>(url, newItem);
	}
}
