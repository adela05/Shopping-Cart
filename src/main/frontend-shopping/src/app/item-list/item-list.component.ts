import { Component, OnInit, OnDestroy } from '@angular/core';
import { Item } from '../item';
import { ItemService } from '../item.service';
import { Subscription } from 'rxjs';
//import {} from '../../assets/img/Toblerone.jpg'

@Component({
	selector: 'app-item-list',
	templateUrl: './item-list.component.html',
	styleUrls: [ './item-list.component.scss' ]
})
export class ItemListComponent implements OnInit, OnDestroy {
	items: Item[] = [];
	search = '';
	shopItemSub: Subscription;
	filterText: string = '';

	constructor(private itemService: ItemService) {}

	ngOnInit() {
		this.getItem();
	}
	ngOnDestroy() {
		if (this.shopItemSub) {
			this.shopItemSub.unsubscribe();
		}
	}
	getItem() {
		this.shopItemSub = this.itemService.getItem().subscribe(
			(res: Item[]) => {
				this.items = res;
			},
			(err) => {
				console.log(err);
			}
		);
	}
}
