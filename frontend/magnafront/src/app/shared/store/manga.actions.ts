import { createAction, props } from '@ngrx/store';
import { Manga } from '../models/mangaDTO';

export const loadMangas = createAction('[Manga] Load Mangas');
export const loadMangasSuccess = createAction('[Manga] Load Mangas Success', props<{ mangas: Manga[] }>());
export const loadMangasFailure = createAction('[Manga] Load Mangas Failure', props<{ error: string }>());