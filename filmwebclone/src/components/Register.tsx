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
        try {
          fetch("http://localhost:8080/auth/register", {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify({
              username: inputs.userName,
              email: inputs.email,
              password: inputs.password,
              role: "NOTADMIN",
            }),
            mode: "cors",
          }).then(async (res) => {
            const r = await res.json();
            if (r.statusCode === 200) {
              console.log("succesfully registered!");
            }
          });
        } catch (e) {
          console.log(e);
        }
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
