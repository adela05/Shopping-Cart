import { Pipe, PipeTransform } from '@angular/core';
import { Item } from './item';

@Pipe({
	name: 'searchItem'
})
export class SearchItemPipe implements PipeTransform {
	transform(value: Item[], searchItem: string): Item[] {
		let matchingText = value.filter((item: Item) => {
			if (item.name && item.name.toLowerCase().includes(searchItem.toLowerCase())) {
				return true;
			}
			if (item.category && item.category.toLowerCase().includes(searchItem.toLowerCase())) {
				return true;
			}
			return false;
		});
		return matchingText;
	}
}
