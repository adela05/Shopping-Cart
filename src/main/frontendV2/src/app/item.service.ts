import { Injectable } from '@angular/core';
import { Item } from './item';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
	providedIn: 'root'
})
export class ItemService {
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
	addItem(item: Item): Observable<Item> {
		const url = `${this.apiUrl}/shopping`;
		return this.http.post<Item>(url, item);
	}
	updateItem(id: number, item: Item): Observable<Item> {
		const url = `${this.apiUrl}/shopping/${id}`;
		return this.http.put<Item>(url, item);
	}
}
