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
}
