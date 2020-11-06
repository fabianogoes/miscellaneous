import { CardService } from './../services/card.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { NavController, AlertController, LoadingController } from '@ionic/angular';
import { ActivatedRoute } from '@angular/router';
import { Card } from '../models/card';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-card-add',
  templateUrl: './card-add.page.html',
  styleUrls: ['./card-add.page.scss'],
})
export class CardAddPage implements OnInit {

  @ViewChild('inputAsk') inputAsk;
  card: Card;
  cardFormGroup: FormGroup;

  constructor(
    public navCtrl: NavController,
    public alertCtrl: AlertController,
    public loadingCtrl: LoadingController,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private service: CardService) {
      this.card = {id: null, ask: null, answer: null};
      this.cardFormGroup = this.formBuilder.group({
        ask: ['', Validators.required],
        answer: ['', Validators.required],
      });
    }

  ngOnInit() {}

  ionViewDidEnter() {
    if (this.route.snapshot.paramMap.get('id') !== null) {
      this.getCard(String(this.route.snapshot.paramMap.get('id')));
    }
  }

  getCard(id: string) {
    this.presentLoading().then((loading) => {
      loading.present();
      this.service.getCard(id).subscribe(card => {
        this.card = card;
        loading.dismiss();
      }, err => {
        console.error(`Error = ${err}`);
        loading.dismiss();
      });
    });
  }

  onSubmit() {
    const id = this.card.id;
    const ask = this.cardFormGroup.controls['ask'].value;
    const answer = this.cardFormGroup.controls['answer'].value;
    this.presentLoading().then(loading => {
      loading.present();
      if (id === null) {
        this.service.addCard({id: null, ask, answer}).then(() => {
          this.cardFormGroup.reset();
          this.inputAsk.setFocus();
          this.presentAlert();
          loading.dismiss();
        }).catch(err => {
          console.error(err);
          loading.dismiss();
        });
      } else {
        this.service.updateCard({id: id, ask, answer}, id).then(() => {
          this.cardFormGroup.reset();
          this.inputAsk.setFocus();
          this.presentAlert();
          loading.dismiss();
        }).catch(err => {
          console.error(err);
          loading.dismiss();
        });  
      }
    });
  }

  async presentAlert() {
    const alert = await this.alertCtrl.create({
      header: 'Success',
      message: 'Card save successfully',
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
