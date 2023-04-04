import React from 'react';
import '../Styling/About.css';

const About = () => {
    return(
        <div className="aboutText">
            <p>
                This  is a Recipe Application developed for users who like to try yummy recipes.
                You can try a random recipe , a recipe by name,
                search a recipe by a list of given ingredients.
                A user can add recipe to a favorite list or remove it.
                The app was created using Java Spring Boot in backend and React TypeScript in the front end.
            </p>
        </div>
    )
}

export default About;
