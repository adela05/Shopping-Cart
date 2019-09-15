import { Injectable } from '@angular/core';
import { Item } from './item';
import { Observable, of } from 'rxjs';

@Injectable({
	providedIn: 'root'
})
export class TransactionService {
	constructor() {}
	purchaseItems(items: Item[]): Observable<Item[]> {
		return of(items);
	}
}
