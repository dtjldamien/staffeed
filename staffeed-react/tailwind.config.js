module.exports = {
  purge: ["./src/**/*.{js,jsx,ts,tsx}", "./public/index.html"],
  darkMode: false, // or 'media' or 'class'
  theme: {
    extend: {
      colors: {
        "accent-red-1": "#FEC5BB",
        "accent-red-2": "#FCD5CE",
        "accent-red-3": "#FAE1DD",
        "accent-red-4": "#F8EDEB",
        "accent-green-1": "#E8E8E4",
        "accent-green-2": "#D8E2DC",
        "accent-green-3": "#ECE4DB",
        "accent-orange-1": "#FFE5D9",
        "accent-orange-2": "#FFD7BA",
        "accent-orange-3": "#FEC89A",
      },
      backgroundImage: {
        "bg-cover": "url(./assets/bg.png)",
      },
    },
  },
  variants: {
    extend: {},
  },
  plugins: [],
};
