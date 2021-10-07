const plugin = require('tailwindcss/plugin');

module.exports = {
  purge: [],
  darkMode: false, // or 'media' or 'class'
  theme: {
    extend: {
      backgroundImage: {
        'bg-cover': 'url(/images/bg.png)',
      },
    },
  },
  variants: {
    extend: {},
  },
  plugins: [
    plugin(function ({ addVariant, e }) {
      addVariant('active-link', ({ modifySelectors, separator }) => {
        modifySelectors(
          ({ className }) =>
            `.${e(`active-link${separator}${className}`)}.active`
        );
      });
    }),
  ],
};
