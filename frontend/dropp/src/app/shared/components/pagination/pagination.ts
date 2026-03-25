import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-pagination',
  imports: [],
  templateUrl: './pagination.html',
  styleUrl: './pagination.css',
  standalone: true,
})
export class Pagination {
  readonly PAGE_SIZES = [1, 5, 10, 25, 50, 100];
  @Input() page = 1;
  @Input() pageSize = 10;
  @Input() totalPages = 0;

  @Output() pageChange = new EventEmitter<number>();
  @Output() pageSizeChange = new EventEmitter<number>();

  next() {
    if (this.page < this.totalPages - 1) {
      this.pageChange.emit(this.page + 1);
    }
  }

  previous() {
    if (this.page > 0) {
      this.pageChange.emit(this.page - 1);
    }
  }

  onPageSizeChange(event: Event) {
    const target = event.target as HTMLSelectElement;
    const pageSize = Number(target.value);
    this.pageSizeChange.emit(pageSize);
  }

}
