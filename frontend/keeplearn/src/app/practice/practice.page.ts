import { CardService } from './../services/card.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { NavController, Slides, AlertController, LoadingController } from '@ionic/angular';
import { Card } from '../models/card';
import { CollectionUtils } from '../utils/collection.utils';

@Component({
  selector: 'app-practice',
  templateUrl: './practice.page.html',
  styleUrls: ['./practice.page.scss'],
})
export class PracticePage implements OnInit {

  @ViewChild('slides') slides: Slides;
  slideOpts = {
    effect: 'coverflow'
  };

  cardList: Card[];
  isAsk: boolean;
  isEnd: boolean;
  currentIndex: number;

  constructor(
    public navCtrl: NavController,
    public alertCtrl: AlertController,
    public loadingCtrl: LoadingController,
    private service: CardService
  ) {}

  ngOnInit() {
  }

  ionViewWillEnter() {
    this.onStart();
  }

  onStart() {
    this.presentLoading().then((loading) => {
      loading.present();
      this.service.getCards().subscribe(response => {
        this.cardList = response;
        this.cardList = CollectionUtils.shuffle(this.cardList);
        this.isAsk = true;
        this.isEnd = false;
        this.currentIndex = 1;
        
        if (this.slides) {
          this.slides.slideTo(0);
        }

        loading.dismiss();
      }, err => {
        console.error(err);
        loading.dismiss();
      });
    });
  }

  onAnswer() {
    this.isAsk = !this.isAsk;
  }

  onHard() {
    this.onNextCard();
  }

  onDone() {
    this.onNextCard();
  }

  onEasy() {
    this.onNextCard();
  }

  onNextCard() {
    this.slides.isEnd().then(response => {
      this.isEnd = response;
      if (this.isEnd) {
        this.presentAlert();
      } else {
        this.slides.slideNext();
        this.isAsk = !this.isAsk;
        this.currentIndex++;
      }
    });
  }

  async presentAlert() {
    const alert = await this.alertCtrl.create({
      header: 'Success',
      message: 'Card Practice finally',
      buttons: ['OK'],
      animated: true,
      mode: 'ios'
    });
    await alert.present();
  }

  async presentLoading() {
    return await this.loadingCtrl.create({message: 'wait, loading data...'});
  }

}
