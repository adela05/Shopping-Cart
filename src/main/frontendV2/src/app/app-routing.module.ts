import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ItemMasterComponent } from './item-master/item-master.component';
import { CartComponent } from './cart/cart.component';

const routes: Routes = [
	{ path: 'shopping', component: ItemMasterComponent },
	{ path: '', redirectTo: 'shopping', pathMatch: 'full' },
	{ path: 'cart', component: CartComponent }
];

@NgModule({
	imports: [ RouterModule.forRoot(routes) ],
	exports: [ RouterModule ]
})
export class AppRoutingModule {}
