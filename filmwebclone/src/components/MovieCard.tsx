import { useState } from "react";
import MovieInfo from "./MovieInfo";

type I = {
  id: number;
  title: string;
  description: string;
  releaseDate: string;
  directorName: string;
  actorNames: string[];
};

type Props = {
  movieItem: I;
  movies: I[];
  setMovies: any;
};
export default function MovieCart({ movieItem, movies, setMovies }: Props) {
  const [movieInfo, setMovieInfo] = useState<boolean>(false);
  return (
    <div className="flex flex-col size-80 bg-black justify-start items-center rounded-lg text-white text-lg border-2 border-white break-words whitespace-break-spaces ">
      <div className="flex  w-full h-4 grad-1"></div>
      <div className="flex  w-full h-4 grad-2"></div>
      <div className=" inline-block line-clamp-1 font-bold text-2xl text-yellow-400 text-center break-words w-full max-w-80 px-4 mt-4">
        <p className="w-full line-clamp-1 px-4">{movieItem.title}</p>
      </div>

      <div className=" inline-block w-full mt-2">
        <p className="w-full line-clamp-3 px-2">{movieItem.description}</p>
      </div>
      <div className="flex text-base w-full px-2 mt-6">
        <div className="opacity-60">Directed by:&nbsp; </div>
        <div className="">{movieItem.directorName}</div>
      </div>
      <button
        className="mt-6 border rounded-lg px-1"
        onClick={() => {
          setMovieInfo(true);
        }}
      >
        SEE MORE
      </button>
      {movieInfo && (
        <MovieInfo
          hide={setMovieInfo}
          movieItem={movieItem}
          movies={movies}
          setMovies={setMovies}
        />
      )}
    </div>
  );
}
// title
// description
// release date
// director
// actors
