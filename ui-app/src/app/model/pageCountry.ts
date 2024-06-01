import { Country } from './country';
import { PageableObject } from './pageableObject';
import { SortObject } from './sortObject';

export interface PageCountry { 
    totalPages?: number;
    totalElements?: number;
    size?: number;
    content?: Array<Country>;
    number?: number;
    sort?: Array<SortObject>;
    first?: boolean;
    last?: boolean;
    numberOfElements?: number;
    pageable?: PageableObject;
    empty?: boolean;
}