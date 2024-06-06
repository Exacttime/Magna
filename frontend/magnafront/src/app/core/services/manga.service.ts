import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Manga } from 'src/app/shared/models/mangaDTO';

@Injectable({
  providedIn: 'root'
})
export class MangaService {
  private apiUrl = 'http://localhost:8080/api/mangas';

  constructor(private http: HttpClient) {}

  getMangas(): Observable<Manga[]> {
    return this.http.get<Manga[]>(this.apiUrl);
  }
}