import {Component, OnInit} from '@angular/core';
import { CommonModule } from '@angular/common';
import {MangaService} from "../../core/services/manga.service";
import {error} from "@angular/compiler-cli/src/transformers/util";

@Component({
  selector: 'app-card-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './card-list.component.html',
  styleUrl: './card-list.component.scss',
})
export class CardListComponent implements OnInit{
  data!: any[];
  constructor(private mangaService: MangaService) {
  }
  ngOnInit() {
    this.mangaService.getMangas().subscribe({
      next: (res) => {
        this.data = res;
        console.log(this.data);
      },
      error: error => {
        console.log(error)
      }
    })
  }
}
