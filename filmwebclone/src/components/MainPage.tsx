import MovieCart from "./MovieCard";

export default function MainPage() {
  return (
    <div className="text-3xl text-white gap-2">
      {" "}
      Movies
      <div className="flex flex-wrap gap-2 justify-center">
        {[1, 2, 3].map((item) => (
          <MovieCart key={item} />
        ))}
      </div>
    </div>
  );
}
