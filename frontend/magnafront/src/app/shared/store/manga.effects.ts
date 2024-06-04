import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { catchError, map, mergeMap } from 'rxjs/operators';
import { of } from 'rxjs';
import { loadMangas, loadMangasFailure, loadMangasSuccess } from './manga.actions';
import { Manga } from '../models/mangaDTO';
import { MangaService } from 'src/app/core/services/manga.service';

@Injectable()
export class MangaEffects {
  loadMangas$ = createEffect(() =>
    this.actions$.pipe(
      ofType(loadMangas),
      mergeMap(() =>
        this.mangaService.getMangas().pipe(
          map((mangas:Manga[]) => loadMangasSuccess({ mangas })),
          catchError(error => of(loadMangasFailure({ error })))
        )
      )
    )
  );

  constructor(
    private actions$: Actions,
    private mangaService: MangaService
  ) {}
}