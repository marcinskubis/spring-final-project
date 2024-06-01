import { useEffect, useState } from "react";
import AddMovieComp from "./AddMovieComp";
import MovieCart from "./MovieCard";

export default function MainPage() {
  const [showTemplate, setShowTemplate] = useState<boolean>(false);
  const [movies, setMovies] = useState<I[]>([]);
  type I = {
    id: number;
    title: string;
    description: string;
    releaseDate: Date;
    directorName: string;
    actorNames: string[];
  };
  useEffect(() => {
    try {
      fetch("http://localhost:8080/movies", {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },

        mode: "cors",
      })
        .then(async (res) => {
          const r = await res.json();
          return r;
        })
        .then((response: I[]) => {
          setMovies(response);
        });
    } catch (e) {
      console.log(e);
    }
  }, []);
  return (
    <div className="flex flex-col relative text-3xl text-white gap-2 items-center w-full min-h-screen">
      <div className="flex flex-col w-full h-full">
        <div className="flex items-center justify-between w-full my-4 px-8">
          <div>Movies</div>
          {(JSON.parse(sessionStorage.getItem("role")!) === "ADMIN" ||
            true) && (
            <button
              className="flex my-2 border-4 border-orange-700 rounded-lg text-base w-fit self-center px-4 py-2 bg-orange-500 hover:scale-110 ease-in-out duration-150"
              onClick={() => {
                setShowTemplate(true);
              }}
            >
              Add Movie
            </button>
          )}
        </div>
        <div className="flex flex-wrap gap-8 justify-center mt-20 h-full p-6">
          {movies.map((item) => (
            <MovieCart
              key={item.id}
              movieItem={item}
              movies={movies}
              setMovies={setMovies}
            />
          ))}
        </div>
      </div>
      {showTemplate && (
        <AddMovieComp hide={setShowTemplate} setMovies={setMovies} />
      )}
    </div>
  );
}
