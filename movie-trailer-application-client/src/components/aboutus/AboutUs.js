import React from 'react';
import './AboutUs.css';

const AboutUs = () => {
  return (
    <div className="about-us-container">
      <div className="card-full">
        <br />
        <h2>Our Story</h2>
        <br />
        <p>
        Welcome to Movie Trailers – your one-stop destination for all things related to movies 
        and trailers! We're passionate about the magic of cinema and dedicated to bringing the 
        silver screen experience right to your fingertips.
        </p>
        <br />
        <h2>Our Mission</h2>
        <br />
        <p>
        At Movie Trailers, our mission is to connect movie enthusiasts from around the globe. 
        We're here to make your cinematic journey exciting and memorable. Whether you're seeking 
        the latest trailers, insightful reviews, or just a place to share your thoughts on your
         favorite films, we've got you covered.       
        </p>
      </div>
      <div className="card-full">
      <br />
        <h2>What We Offer</h2>
        <br />
        <ul>
        <p>
            <li>Trailers Galore: Explore a vast collection of movie trailers from various genres.
                 Get a sneak peek into the latest blockbusters and hidden gems.</li><br />
            <li>In-Depth Reviews: Dive into comprehensive reviews and analysis of both classic 
                and current films. Find out what's worth watching and what to skip.</li><br />
            <li>Community Engagement: Join our vibrant movie-loving community. Share your 
                thoughts, engage in discussions, and connect with fellow film buffs.</li><br />
            <li>Personalized Recommendations: Let us help you discover new movies tailored to 
                your taste. Our recommendation system will introduce you to films you might 
                have otherwise missed.</li>
            </p>
        </ul>
        
      </div>
      <div className="card-full">
        <h2>Join Us on this Cinematic Journey</h2>
        <br />
        <p>
        Whether you're a casual moviegoer or a die-hard cinephile, Movie Trailers is here to 
        enhance your movie-watching experience. We're committed to delivering the latest and 
        greatest in the world of cinema. So, grab your popcorn, take a seat, and embark on a 
        thrilling journey through the world of movies with us.

        Thank you for being a part of our cinematic community. We look forward to sharing the
        magic of movies with you!
        </p>
      </div>
      <div className="footer">
        &copy; 2023 Movie Trailers
      </div>
    </div>
  );
};

export default AboutUs;
