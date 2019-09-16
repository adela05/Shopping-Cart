import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Item } from '../item';
import { CheckoutService } from '../checkout.service';
import { Receipt } from '../receipt';

@Component({
	selector: 'app-cart',
	templateUrl: './cart.component.html',
	styleUrls: [ './cart.component.scss' ]
})
export class CartComponent implements OnInit {
	itemCart: Item[] = [];
	total = 0;
	apiUrl = '';
	receipt = {};
	isReceipt: boolean = false;
	constructor(private checkoutService: CheckoutService, private router: Router) {}

	ngOnInit() {
		this.getItemCart();
	}

	onRemoveItemCart(index: number) {
		this.checkoutService.removeItemCart(index);
		this.getItemCart();
	}
	getItemCart() {
		this.itemCart = this.checkoutService.getItemCart();
	}
	onPurchaseItems() {
		this.checkoutService.purchaseItems(this.itemCart).subscribe((res: any) => {
			this.isReceipt = true;
			this.receipt = res;
			this.checkoutService.emptyCart();
			this.itemCart = [];
		});
	}
	// add total here
}
