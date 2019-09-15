import { Pipe, PipeTransform } from '@angular/core';
import { Item } from './item';

@Pipe({
	name: 'search'
})
export class SearchPipe implements PipeTransform {
	transform(value: Item[], filterText: string): Item[] {
		let matchingText = value.filter((item: Item) => {
			if (item.name && item.name.toLowerCase().includes(filterText.toLowerCase())) {
				return true;
			}
			if (item.category && item.category.toLowerCase().includes(filterText.toLowerCase())) {
				return true;
			}
			return false;
		});
		return matchingText;
	}
}
