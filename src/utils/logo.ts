import chalk from 'chalk';
import figlet from 'figlet';

export const logo = () => {
  return console.log(
    chalk.green.bold(
      figlet.textSync('\Taskr', {
        font: 'Big',
        width: 80,
      }),
    ),
    '\n',
  );
};
