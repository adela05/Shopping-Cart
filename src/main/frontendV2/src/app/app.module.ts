import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { ItemMasterComponent } from './item-master/item-master.component';
import { ItemCardComponent } from './item-master/item-card/item-card.component';
import { SearchItemPipe } from './search-item.pipe';
import { CartComponent } from './cart/cart.component';

@NgModule({
	declarations: [ AppComponent, NavBarComponent, ItemMasterComponent, ItemCardComponent, SearchItemPipe, CartComponent ],
	imports: [ BrowserModule, AppRoutingModule, HttpClientModule, FormsModule ],
	providers: [],
	bootstrap: [ AppComponent ]
})
export class AppModule {}
