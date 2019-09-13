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
	getItem(): Observable<Item[]> {
		const url = `${this.apiUrl}/shopping`;
		return this.http.get<Item[]>(url);
	}

	deleteItem(id: number): Observable<Item> {
		const url = `${this.apiUrl}/shopping/${id}`;
		return this.http.delete<Item>(url);
	}
	updateItem(id: number, item: Item): Observable<Item> {
		const url = `${this.apiUrl}/shopping/${id}`;
		return this.http.put<Item>(url, item);
	}
}
