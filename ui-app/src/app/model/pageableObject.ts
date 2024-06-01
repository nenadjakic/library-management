import { SortObject } from './sortObject';

export interface PageableObject { 
    offset?: number;
    sort?: Array<SortObject>;
    unpaged?: boolean;
    paged?: boolean;
    pageNumber?: number;
    pageSize?: number;
}