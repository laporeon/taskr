import { TaskInput } from '@interfaces/Task';
import { TaskRepository } from '@repositories/task.repository';
import { renderTaskboard } from '@utils/taskboard';

export class TaskService {
  constructor(private readonly taskRepository: TaskRepository) {}

  async create({ title, priority }: TaskInput) {
    await this.taskRepository.add({ title, priority });
  }

  async list(status: string) {
    const tasks = await this.taskRepository.get(status);
    renderTaskboard(tasks);
  }

  async delete(id: number) {
    await this.taskRepository.delete(id);
  }
}
