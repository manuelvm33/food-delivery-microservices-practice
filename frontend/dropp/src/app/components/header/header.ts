import { Component, signal } from '@angular/core';

@Component({
  selector: 'app-header',
  imports: [],
  templateUrl: './header.html',
  styleUrl: './header.css',
  standalone: true,
})
export class Header {

  username = signal<string>('Alfredo');
}
