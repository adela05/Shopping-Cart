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
	text = 'No items in cart';
	apiUrl = '';
	receipt = {};
	isReceipt: boolean = true;
	constructor(private checkoutService: CheckoutService, private router: Router) {}

	ngOnInit() {
		this.getItemCart();
	}
	onReceiptDisplay(i: number) {
		this.isReceipt = !this.isReceipt;
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
			this.receipt = res;
			this.checkoutService.emptyCart();
			this.itemCart = [];
			this.text = 'Thank you for your business! Redirecting to Homepage';

			setTimeout(() => {
				this.receipt;
			}, 1500);
		});
	}
	// add total here
}
