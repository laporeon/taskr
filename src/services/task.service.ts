import { TaskStatus } from '@enums/TaskStatus';
import { CreateTaskInput, Task } from '@interfaces/Task';
import { TaskRepository } from '@repositories/task.repository';
import { renderTaskboard } from '@utils/taskboard';

export class TaskService {
  constructor(private readonly taskRepository: TaskRepository) {}

  async create({ title, priority }: CreateTaskInput) {
    await this.taskRepository.add({ title, priority });
  }

  async list(status?: TaskStatus) {
    const tasks = await this.taskRepository.get(status);
    renderTaskboard(tasks);
  }

  async update(id: number, options: Partial<Task>) {
    await this.taskRepository.update(id, options);
  }

  async delete(id: number) {
    await this.taskRepository.delete(id);
  }
}
