export interface RecipeModel {
    id: number
    title: string
    extendedIngredients: ExtendedIngredient[]
    instructions: string
    image: string
}

export interface ExtendedIngredient {
    id: number
    aisle: string
    image: string
    name: string
    amount: number
    unit: string
    unitShort: string
    unitLong: string
    originalString: string
    metaInformation: string[]
    measures: Measures
}

export interface Measures {
    us: Us
    metric: Metric
}

export interface Us {
    amount: number
    unitShort: string
    unitLong: string
}

export interface Metric {
    amount: number
    unitShort: string
    unitLong: string
}

export interface SearchRecipeResponse {
    results: Result[];
}

export interface Result {
    id:    string;
    title: string;
    image: string;
}
