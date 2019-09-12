import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ItemListComponent } from './item-list/item-list.component';
import { CartComponent } from './cart/cart.component';

const routes: Routes = [
	{ path: 'shopping', component: ItemListComponent },
	{ path: 'cart', component: CartComponent },
	{ path: '', redirectTo: 'shopping', pathMatch: 'full' }
];

@NgModule({
	imports: [ RouterModule.forRoot(routes) ],
	exports: [ RouterModule ]
})
export class AppRoutingModule {}
