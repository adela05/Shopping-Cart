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
	qtyToPurchase = 1;
	constructor(private cartService: CartService) {}

	ngOnInit() {}
	onAddToCart(item: Item) {
		if (this.qtyToPurchase > 0 && this.qtyToPurchase <= item.quantity) {
			this.cartService.addToCart(item, this.qtyToPurchase);
			this.buttonText = 'Added';

			setTimeout(() => {
				this.buttonText = 'Add to Cart';
			}, 1500);
		}
	}
}
