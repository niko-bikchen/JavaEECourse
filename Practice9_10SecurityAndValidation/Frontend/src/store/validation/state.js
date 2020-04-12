export default function() {
  return {
    book: {
      author: [
        value =>
          /^[\w.]+ [\w.]+( [\w.]+)?( [\w.]+)?$/.test(value) ||
          'Author name should consist of two to four words separated by a whitespace',
        value =>
          value
            .split(' ')
            .map(word => /^[a-zA-Z'.]+$/.test(word))
            .reduce((ac, cv) => ac && cv) ||
          'Author name can contain only letters, dots and apostrophes '
      ],
      title: [value => value !== '' || 'Book title cannot be empty'],
      isbn: [
        value => /^\d{3}-\d-\d{2}-\d{6}-\d$/.test(value) || 'ISBN should contain 13 digits',
        value => value !== '' || 'ISBN cannot be empty'
      ]
    },
    general: {
      notEmpty: [value => value !== '' || 'Field cannot be empty']
    },
    user: {
      password: [value => value.length >= 8 || 'Password should be at least 8 symbols long'],
      login: [
        value =>
          /^[a-zA-Z0-9_]+$/.test(value) || 'Login can contain only letters, numbers and underscores'
      ]
    }
  };
}
