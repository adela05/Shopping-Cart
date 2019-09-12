export class Item {
	id: number;
	name: string;
	price: number;
	quantity: number;
	category: string;
	isDomestic: boolean;
	imgUrl: string;

	constructor(name: string, price: number, quantity: number, category: string, imgUrl: string) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.category = category;
		this.imgUrl = this.imgUrl;
	}
}
