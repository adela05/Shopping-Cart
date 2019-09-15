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
	text = '';
	total = 0;

	constructor(
		private cartService: CartService,
		private transactionService: TransactionService,
		private router: Router
	) {}

	ngOnInit() {
		this.getItemInCart();
	}
	onRemoveFromCart(index: number) {
		this.cartService.deleteByIndex(index);
		this.getItemInCart();
	}
	getItemInCart() {
		this.itemInCart = this.cartService.getItemInCart();
	}

	calTotal() {
		this.total = 0;
		this.itemInCart.forEach((i) => {
			if (i.price) {
				this.total += i.price;
			}
		});
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
}
