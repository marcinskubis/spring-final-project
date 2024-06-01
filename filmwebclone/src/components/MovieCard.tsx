import { useState } from "react";
import MovieInfo from "./MovieInfo";

type I = {
  id: number;
  title: string;
  description: string;
  releaseDate: Date;
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
    <div className="flex flex-col size-72 bg-black justify-start items-center rounded-lg text-white text-lg border-2 border-white overflow-hidden">
      <div className="flex  w-full h-4 grad-1"></div>
      <div className="flex  w-full h-4 grad-2"></div>
      <div className="flex font-bold mt-4 text-2xl text-yellow-400">
        {movieItem.title.toUpperCase()}
      </div>
      <div className="px-2 line-clamp-2 mt-4 text-center">
        {movieItem.description}
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
