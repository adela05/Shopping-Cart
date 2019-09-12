import { Component, OnInit, OnDestroy } from '@angular/core';
import { Item } from '../item';
import { ItemService } from '../item.service';
import { Subscription } from 'rxjs';

@Component({
	selector: 'app-item-list',
	templateUrl: './item-list.component.html',
	styleUrls: [ './item-list.component.scss' ]
})
export class ItemListComponent implements OnInit, OnDestroy {
	allItems: Item[] = [];
	searchTerm = '';
	getSub: Subscription;

	constructor(private itemService: ItemService) {}

	ngOnInit() {
		this.getItem();
	}
	ngOnDestroy() {
		if (this.getSub) {
			this.getSub.unsubscribe();
		}
	}
	getItem() {
		this.getSub = this.itemService.getItem().subscribe(
			(res: any) => {
				this.allItems = res;
			},
			(err) => {
				console.log(err);
			}
		);
	}
}
