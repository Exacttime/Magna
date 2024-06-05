import { createFeatureSelector, createSelector } from '@ngrx/store';
import { MangaState } from './manga.reducers';

export const selectMangaState = createFeatureSelector<MangaState>('mangas');

export const selectAllMangas = createSelector(
  selectMangaState,
  (state: MangaState) => state.mangas
);

export const selectMangaLoading = createSelector(
  selectMangaState,
  (state: MangaState) => state.loading
);

export const selectMangaError = createSelector(
  selectMangaState,
  (state: MangaState) => state.error
);