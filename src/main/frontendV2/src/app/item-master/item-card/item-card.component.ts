import { Component, OnInit, Input } from '@angular/core';
import { Item } from '../../item';
import { CheckoutService } from '../../checkout.service';

@Component({
	selector: 'app-item-card',
	templateUrl: './item-card.component.html',
	styleUrls: [ './item-card.component.scss' ]
})
export class ItemCardComponent implements OnInit {
	@Input() item: Item;
	buttonTitle = 'Add to Cart';
	purchaseQty = 1;
	editingQty = {};

	constructor(private checkoutService: CheckoutService) {}

	ngOnInit() {}
	onAddCart(item: Item) {
		this.checkoutService.addCart(item, item.quantity);
		this.buttonTitle = 'Added!';

		setTimeout(() => {
			this.buttonTitle = 'Add to Cart';
		}, 2500);
	}
	onStartEdit(i: number) {
		this.editingQty[i] = true;
	}

	onSaveEdit(i: number) {
		this.editingQty[i] = false;
	}

	// onAddCart(item: Item) {
	// 	if (this.purchaseQty > 0 && this.purchaseQty <= item.quantity) {
	// 		this.checkoutService.addCart(item, this.purchaseQty);
	// 		this.buttonTitle = 'Added';

	// 		setTimeout(() => {
	// 			this.buttonTitle = 'Add to Cart';
	// 		}, 1500);
	// 	}
	// }
}
