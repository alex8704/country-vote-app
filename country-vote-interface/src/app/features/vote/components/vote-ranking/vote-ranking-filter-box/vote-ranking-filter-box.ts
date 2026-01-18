import {Component, OnInit, output} from '@angular/core';
import {distinctUntilChanged} from 'rxjs';
import {FormControl, ReactiveFormsModule} from '@angular/forms';

@Component({
  selector: 'app-vote-ranking-filter-box',
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './vote-ranking-filter-box.html',
  styleUrl: './vote-ranking-filter-box.scss',
})
export class VoteRankingFilterBox implements OnInit {
  searchTermControl= new FormControl('');
  termChanged = output<string>();

  ngOnInit(): void {
    this.searchTermControl.valueChanges
      .pipe(
        distinctUntilChanged()
      )
      .subscribe(value => this.termChanged.emit(value ?? ''))
  }
}
