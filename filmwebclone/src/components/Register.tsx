import { FormEvent, useState } from "react";
import Button from "./SubmitFormButton";

export default function Register() {
  const [inputs, setInputs] = useState({
    userName: "",
    email: "",
    password: "",
    passwordrepeat: "",
  });
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
      autoComplete="off"
      className="flex flex-col gap-4 size-fit m-4 text-4xl  text-white"
      onSubmit={(e: FormEvent) => {
        e.preventDefault();
        console.log(inputs);
        //api fetch here
      }}
    >
      <div>Register</div>
      <label htmlFor="userName">
        <input
          name="userName"
          placeholder="user name"
          type="text"
          className=" outline-none text-[1rem] rounded-md text-black px-4 w-64"
          onChange={handleChange}
        />
      </label>
      <label htmlFor="email">
        <input
          name="email"
          placeholder="email"
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
      <label htmlFor="passwordrepeat">
        <input
          name="passwordrepeat"
          type="password"
          placeholder="repeat password"
          className=" outline-none text-[1rem] rounded-md text-black px-4 w-64"
          onChange={handleChange}
        />
      </label>
      <Button />
    </form>
  );
}
