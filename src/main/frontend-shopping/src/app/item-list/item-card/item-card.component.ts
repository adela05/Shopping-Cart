import { Component, OnInit, Input } from '@angular/core';
import { Item } from '../../item';
import { CartService } from '../../cart.service';

@Component({
	selector: 'app-item-card',
	templateUrl: './item-card.component.html',
	styleUrls: [ './item-card.component.scss' ]
})
export class ItemCardComponent implements OnInit {
	@Input() item: Item;
	buttonText = 'Add to Cart';
	purchaseQty = 1;

	constructor(private cartService: CartService) {}

	ngOnInit() {}
	onAddToCart(item: Item) {
		if (this.purchaseQty > 0 && this.purchaseQty <= item.quantity) {
			this.cartService.addToCart(item, this.purchaseQty);
			this.buttonText = 'Added!';
		}

		setTimeout(() => {
			this.buttonText = 'Add to Cart';
		}, 1500);
	}
}
