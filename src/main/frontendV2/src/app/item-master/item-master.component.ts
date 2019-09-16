import { Component, OnInit, OnDestroy } from '@angular/core';
import { Item } from '../item';
import { ItemService } from '../item.service';
import { Subscription } from 'rxjs';

@Component({
	selector: 'app-item-master',
	templateUrl: './item-master.component.html',
	styleUrls: [ './item-master.component.scss' ]
})
export class ItemMasterComponent implements OnInit, OnDestroy {
	allItems: Item[] = [];
	searchItem = '';
	shopSub: Subscription;
	constructor(private itemService: ItemService) {}

	ngOnInit() {
		this.getItem();
	}
	ngOnDestroy() {
		if (this.shopSub) {
			this.shopSub.unsubscribe();
		}
	}
	getItem() {
		this.shopSub = this.itemService.getItem().subscribe(
			(res: any) => {
				this.allItems = res;
			},
			(err) => {
				console.log(err);
			}
		);
	}
}
