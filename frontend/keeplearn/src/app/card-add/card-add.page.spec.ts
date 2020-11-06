import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CardAddPage } from './card-add.page';

describe('CardAddPage', () => {
  let component: CardAddPage;
  let fixture: ComponentFixture<CardAddPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CardAddPage ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CardAddPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
