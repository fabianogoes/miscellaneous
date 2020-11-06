import { Component, OnInit } from '@angular/core';
import { NavController, LoadingController } from '@ionic/angular';
import { Card } from '../models/card';
import { CardService } from '../services/card.service';
import { load } from '@angular/core/src/render3';

@Component({
  selector: 'app-card-list',
  templateUrl: './card-list.page.html',
  styleUrls: ['./card-list.page.scss'],
})
export class CardListPage implements OnInit {

  cards: Card[];

  constructor(
    public navCtrl: NavController,
    public loadingCtrl: LoadingController,
    private service: CardService) { }

  ngOnInit() {}

  ionViewWillEnter() {
    this.onList();
  }

  onList() {
    this.presentLoading().then((loading) => {
      loading.present();
      this.service.getCards().subscribe(data => {
        this.cards = data;
        loading.dismiss();
      }, err => {
        console.error(err);
        loading.dismiss();
      });
    });    
  }

  onDelete(id: string) {
    this.presentLoading().then((loading) => {
      loading.present();
      this.service.removeCard(id).then(() => {
        this.onList();
        loading.dismiss();
      }).catch(err => {
        console.error(err);
        loading.dismiss();
      });
    });
  }

  async presentLoading() {
    return await this.loadingCtrl.create({message: 'wait, loading data...'});
  }

}
