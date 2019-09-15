import { Component, OnInit } from '@angular/core';
import { Item } from '../item';
import { CartService } from '../cart.service';
import { TransactionService } from '../transaction.service';
import { Router } from '@angular/router';

@Component({
	selector: 'app-cart',
	templateUrl: './cart.component.html',
	styleUrls: [ './cart.component.scss' ]
})
export class CartComponent implements OnInit {
	itemInCart: Item[] = [];
	text = 'No items in cart';
	subTotal = 0;
	apiUrl = '';

	constructor(
		private cartService: CartService,
		private transactionService: TransactionService,
		private router: Router
	) {}

	ngOnInit() {
		this.getItemInCart();
		//this.calTotal();
	}
	onRemoveFromCart(index: number) {
		this.cartService.deleteByIndex(index);
		this.getItemInCart();
		//this.calTotal();
	}
	getItemInCart() {
		this.itemInCart = this.cartService.getItemInCart();
	}

	onPurchase() {
		this.transactionService.purchaseItems(this.itemInCart).subscribe((res: any) => {
			this.cartService.emptyCart();
			this.itemInCart = [];
			this.text = 'Thank You for your business! Redirecting to homepage.';

			setTimeout(() => {
				this.router.navigate([ '/shopping' ]);
			}, 1500);
		});
	}
	// calTotal() {
	// 	this.subTotal = this.getItemInCart.reduce(
	// 		(subTotal, currVal) => subTotal + currVal.price * currVal.quantity,
	// 		0
	// 	);
	// }
}
