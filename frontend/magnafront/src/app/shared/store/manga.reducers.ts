import { createReducer, on } from '@ngrx/store';
import { Manga } from '../models/mangaDTO';
import { loadMangas, loadMangasSuccess, loadMangasFailure } from './manga.actions';

export interface MangaState {
  mangas: Manga[];
  loading: boolean;
  error: string;
}

export const initialState: MangaState = {
  mangas: [],
  loading: false,
  error: ''
};

export const mangaReducer = createReducer(
  initialState,
  on(loadMangas, state => ({ ...state, loading: true })),
  on(loadMangasSuccess, (state, { mangas }) => ({ ...state, mangas, loading: false })),
  on(loadMangasFailure, (state, { error }) => ({ ...state, error, loading: false }))
);
