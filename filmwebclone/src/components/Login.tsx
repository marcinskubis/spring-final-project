import { Link, Navigate } from "react-router-dom";
import Button from "./SubmitFormButton";
import { ChangeEvent, FormEvent, useState } from "react";

export default function Login() {
  const [inputs, setInputs] = useState({ userName: "", password: "" });

  const handleChange = (e: any) => {
    const { name, value } = e.target;
    setInputs((prevVal) => {
      return {
        ...prevVal,
        [name]: value,
      };
    });
  };
  return (
    <form
      className="flex flex-col gap-4 size-fit m-4 text-4xl  text-white"
      onSubmit={(e: FormEvent) => {
        e.preventDefault();
        console.log(inputs);
        //api fetch here
      }}
    >
      <div className="flex w-full justify-between">
        <div>Sign In</div>
        <Link to="/register" className="flex text-sm self-end text-orange-400">
          sign up
        </Link>
      </div>
      <label htmlFor="userName">
        <input
          name="userName"
          placeholder="user name"
          type="text"
          className=" outline-none text-[1rem] rounded-md text-black px-4 w-64"
          onChange={handleChange}
        />
      </label>
      <label htmlFor="password">
        <input
          name="password"
          type="password"
          placeholder="password"
          className=" outline-none text-[1rem] rounded-md text-black px-4 w-64"
          onChange={handleChange}
        />
      </label>

      <Button />
    </form>
  );
}
