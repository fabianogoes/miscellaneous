import { Injectable } from '@angular/core';
import { AngularFirestore, AngularFirestoreCollection } from 'angularfire2/firestore';
// import { HttpClient } from '@angular/common/http';
import { Card } from '../models/card';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CardService {

  private cardsCollection: AngularFirestoreCollection<Card>;
  // private cards: Observable<Card[]>;

  constructor(private db: AngularFirestore) {
    this.cardsCollection = db.collection<Card>('cards');
  }

  getCards(): Observable<Card[]> {
    return this.db.collection<Card>('cards').snapshotChanges().pipe(
      map(actions => {
        return actions.map(a => {
          return a.payload.doc.data();
        });
      })
    );
  }

  getCard(id: string) {
    return this.cardsCollection.doc<Card>(id).valueChanges();
  }

  updateCard(card: Card, id: string) {
    return this.cardsCollection.doc(id).update(card);
  }

  addCard(card: Card) {
    card.id = this.db.createId();
    return this.cardsCollection.doc<Card>(card.id).set(card);
  }

  removeCard(id) {
    return this.cardsCollection.doc(id).delete();
  }

}
