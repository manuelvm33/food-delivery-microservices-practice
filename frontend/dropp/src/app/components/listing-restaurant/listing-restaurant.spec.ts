import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListingRestaurant } from './listing-restaurant';

describe('ListingRestaurant', () => {
  let component: ListingRestaurant;
  let fixture: ComponentFixture<ListingRestaurant>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListingRestaurant],
    }).compileComponents();

    fixture = TestBed.createComponent(ListingRestaurant);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
